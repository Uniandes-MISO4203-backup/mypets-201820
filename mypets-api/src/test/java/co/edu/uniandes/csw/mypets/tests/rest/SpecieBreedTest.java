/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.mypets.tests.rest;

import co.edu.uniandes.csw.auth.api.AuthenticationApi;
import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.mypets.entities.SpecieEntity;
import co.edu.uniandes.csw.mypets.entities.BreedEntity;
import co.edu.uniandes.csw.mypets.dtos.detail.SpecieDetailDTO;
import co.edu.uniandes.csw.mypets.dtos.detail.BreedDetailDTO;
import co.edu.uniandes.csw.mypets.resources.SpecieResource;
import co.edu.uniandes.csw.mypets.tests.Utils;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/*
 * Testing URI: species/
 */
@RunWith(Arquillian.class)
public class SpecieBreedTest {

    private WebTarget target;
    private PodamFactory factory = new PodamFactoryImpl();
    private final String apiPath = Utils.apiPath;
    private final String username = Utils.username;
    private final String password = Utils.password;

    private final int Ok = Status.OK.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();

    private final static List<BreedEntity> oraculo = new ArrayList<>();
    private  AuthenticationApi auth;

    private final String speciePath = "species";
    private final String breedPath = "breed";

    private SpecieEntity fatherSpecieEntity;

    @ArquillianResource
    private URL deploymentURL;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Se agrega las dependencias
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importRuntimeDependencies().resolve()
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(SpecieResource.class.getPackage())
                .addPackage("co.edu.uniandes.csw.auth.properties")
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
    }

    private WebTarget createWebTarget() {
        return ClientBuilder.newClient().target(deploymentURL.toString()).path(apiPath);
    }

    @PersistenceContext(unitName = "mypetsPU")
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private void clearData() {
        List<BreedEntity> records = em.createQuery("SELECT u FROM BreedEntity u").getResultList();
        for (BreedEntity record : records) {
            em.remove(record);
        }
        em.createQuery("delete from SpecieEntity").executeUpdate();
        oraculo.clear();
    }

   /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
            fatherSpecieEntity = factory.manufacturePojo(SpecieEntity.class);
            em.persist(fatherSpecieEntity);

            for (int i = 0; i < 3; i++) {
                BreedEntity breed = factory.manufacturePojo(BreedEntity.class);
                em.persist(breed);
                if(i<2){                
                    breed.setSpecie(fatherSpecieEntity);
                }
                oraculo.add(breed);
            }
    }

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void setUpTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();            
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        target = createWebTarget()
                .path(speciePath)
                .path(fatherSpecieEntity.getId().toString())
                .path(breedPath);
    }

    /**
     * Login para poder consultar los diferentes servicios
     *
     * @param username Nombre de usuario
     * @param password Clave del usuario
     * @return Cookie con información de la sesión del usuario
     * @generated
     */
    public String login() throws IOException, UnirestException, JSONException, InterruptedException, ExecutionException { 
        auth=new AuthenticationApi();
        UserDTO user = new UserDTO();
        user.setUserName(auth.getProp().getProperty("username").trim());
        user.setPassword(auth.getProp().getProperty("password").trim());
        JSONObject json = new JSONObject(auth.authenticationToken(user).getBody()); 
        return (String)json.get("id_token");
    }
   
    public String getUsername() throws IOException, UnirestException, JSONException, InterruptedException, ExecutionException{
     auth=new AuthenticationApi();
    return auth.getProp().getProperty("username").trim();
    }

    /**
     *Prueba para asociar un Breed existente a un Specie
     *
     * @generated
     */
    @Test
    public void addBreedTest() throws IOException, UnirestException, JSONException, InterruptedException, ExecutionException{
        String token= login();

        BreedDetailDTO breed = new BreedDetailDTO(oraculo.get(2));

        Response response = target.path(breed.getId().toString())
                .request()
                .cookie("username",getUsername())
                .cookie("id_token",token)
                .post(Entity.entity(breed, MediaType.APPLICATION_JSON));

        BreedDetailDTO breedTest = (BreedDetailDTO) response.readEntity(BreedDetailDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(breed.getId(), breedTest.getId());
    }

    /**
     * Prueba para obtener una colección de instancias de Breed asociadas a una instancia Specie
     *
     * @generated
     */
    @Test
    public void listBreedTest() throws IOException, UnirestException, JSONException, InterruptedException, ExecutionException {
        String token= login();

        Response response = target
                .request()
                .cookie("username",getUsername())
                .cookie("id_token",token)
                .get();

        String breedList = response.readEntity(String.class);
        List<BreedDetailDTO> breedListTest = new ObjectMapper().readValue(breedList, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(2, breedListTest.size());
    }

    /**
     * Prueba para obtener una instancia de Breed asociada a una instancia Specie
     *
     * @generated
     */
    @Test
    public void getBreedTest() throws IOException, UnirestException, JSONException, InterruptedException, ExecutionException {
        String token= login();
        BreedDetailDTO breed = new BreedDetailDTO(oraculo.get(0));

        BreedDetailDTO breedTest = target.path(breed.getId().toString())
                .request()
                .cookie("username",getUsername())
                .cookie("id_token",token)
                .get(BreedDetailDTO.class);

        Assert.assertEquals(breed.getId(), breedTest.getId());
        Assert.assertEquals(breed.getDescription(), breedTest.getDescription());
        Assert.assertEquals(breed.getName(), breedTest.getName());
        Assert.assertEquals(breed.getMood(), breedTest.getMood());
        Assert.assertEquals(breed.getSize(), breedTest.getSize());
        Assert.assertEquals(breed.getLifeExpectancy(), breedTest.getLifeExpectancy());
    }

    /**
     * Prueba para desasociar un Breed existente de un Specie existente
     *
     * @generated
     */
    @Test
    public void removeBreedTest() throws IOException, UnirestException, JSONException, InterruptedException, ExecutionException {
        String token= login();

        BreedDetailDTO breed = new BreedDetailDTO(oraculo.get(0));

        Response response = target.path(breed.getId().toString())
                .request()
                .cookie("username",getUsername())
                .cookie("id_token",token)
                .delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
