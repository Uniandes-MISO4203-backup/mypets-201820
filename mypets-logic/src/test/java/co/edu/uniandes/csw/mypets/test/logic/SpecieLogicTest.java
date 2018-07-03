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
package co.edu.uniandes.csw.mypets.test.logic;

import co.edu.uniandes.csw.mypets.ejbs.SpecieLogic;
import co.edu.uniandes.csw.mypets.api.ISpecieLogic;
import co.edu.uniandes.csw.mypets.entities.SpecieEntity;
import co.edu.uniandes.csw.mypets.persistence.SpeciePersistence;
import co.edu.uniandes.csw.mypets.entities.BreedEntity;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;



/**
 * @generated
 */
@RunWith(Arquillian.class)
public class SpecieLogicTest {

    /**
     * @generated
     */

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private ISpecieLogic specieLogic;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    private UserTransaction utx;

    /**
     * @generated
     */
    private List<SpecieEntity> data = new ArrayList<SpecieEntity>();
    /**
     * @generated
     */
    private List<BreedEntity> breedData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SpecieEntity.class.getPackage())
                .addPackage(SpecieLogic.class.getPackage())
                .addPackage(ISpecieLogic.class.getPackage())
                .addPackage(SpeciePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
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
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from BreedEntity").executeUpdate();
        em.createQuery("delete from SpecieEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
            for (int i = 0; i < 3; i++) {
                BreedEntity breed = factory.manufacturePojo(BreedEntity.class);
                em.persist(breed);
                breedData.add(breed);
            }
        for (int i = 0; i < 3; i++) {
            SpecieEntity entity = factory.manufacturePojo(SpecieEntity.class);

            em.persist(entity);
            data.add(entity);

            if (i == 0) {
                breedData.get(i).setSpecie(entity);
            }
        }
    }
    /**
     * Prueba para crear un Specie
     *
     * @generated
     */
    @Test
    public void createSpecieTest()  {
        SpecieEntity newEntity = factory.manufacturePojo(SpecieEntity.class);
        SpecieEntity result = specieLogic.createSpecie(newEntity);
        Assert.assertNotNull(result);
        SpecieEntity entity = em.find(SpecieEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Species
     *
     * @generated
     */
    @Test
    public void getSpeciesTest() {
        List<SpecieEntity> list = specieLogic.getSpecies();
        Assert.assertEquals(data.size(), list.size());
        for (SpecieEntity entity : list) {
            boolean found = false;
            for (SpecieEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Specie
     *
     * @generated
     */
    @Test
    public void getSpecieTest() {
        SpecieEntity entity = data.get(0);
        SpecieEntity resultEntity = specieLogic.getSpecie(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * Prueba para eliminar un Specie
     *
     * @generated
     */
    @Test
    public void deleteSpecieTest() {
        SpecieEntity entity = data.get(0);
        specieLogic.removeBreed(entity.getId(), breedData.get(0).getId());
        specieLogic.deleteSpecie(entity.getId());
        SpecieEntity deleted = em.find(SpecieEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Specie
     *
     * @generated
     */
    @Test
    public void updateSpecieTest() {
        SpecieEntity entity = data.get(0);
        SpecieEntity pojoEntity = factory.manufacturePojo(SpecieEntity.class);

        pojoEntity.setId(entity.getId());

        specieLogic.updateSpecie(pojoEntity);

        SpecieEntity resp = em.find(SpecieEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }

    /**
     * Prueba para obtener una instancia de Breed asociada a una instancia Specie
     *
     * @generated
     */
    @Test
    public void getBreedTest() {
        SpecieEntity entity = data.get(0);
        BreedEntity breedEntity = breedData.get(0);
        BreedEntity response = specieLogic.getBreed(entity.getId(), breedEntity.getId());

        Assert.assertEquals(breedEntity.getId(), response.getId());
        Assert.assertEquals(breedEntity.getDescription(), response.getDescription());
        Assert.assertEquals(breedEntity.getName(), response.getName());
        Assert.assertEquals(breedEntity.getMood(), response.getMood());
        Assert.assertEquals(breedEntity.getSize(), response.getSize());
        Assert.assertEquals(breedEntity.getLifeExpectancy(), response.getLifeExpectancy());
    }

    /**
     * Prueba para obtener una colección de instancias de Breed asociadas a una instancia Specie
     *
     * @generated
     */
    @Test
    public void listBreedTest() {
        List<BreedEntity> list = specieLogic.listBreed(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     *Prueba para asociar un Breed existente a un Specie
     *
     * @generated
     */
    @Test
    public void addBreedTest() {
        SpecieEntity entity = data.get(0);
        BreedEntity breedEntity = breedData.get(1);
        BreedEntity response = specieLogic.addBreed(entity.getId(), breedEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(breedEntity.getId(), response.getId());
    }

    /**
     * Prueba para remplazar las instancias de Breed asociadas a una instancia de Specie
     *
     * @generated
     */
    @Test
    public void replaceBreedTest() {
        SpecieEntity entity = data.get(0);
        List<BreedEntity> list = breedData.subList(1, 3);
        specieLogic.replaceBreed(entity.getId(), list);

        entity = specieLogic.getSpecie(entity.getId());
        Assert.assertFalse(entity.getBreed().contains(breedData.get(0)));
        Assert.assertTrue(entity.getBreed().contains(breedData.get(1)));
        Assert.assertTrue(entity.getBreed().contains(breedData.get(2)));
    }

    /**
     * Prueba para desasociar un Breed existente de un Specie existente
     *
     * @generated
     */
    @Test
    public void removeBreedTest() {
        specieLogic.removeBreed(data.get(0).getId(), breedData.get(0).getId());
        BreedEntity response = specieLogic.getBreed(data.get(0).getId(), breedData.get(0).getId());
        Assert.assertNull(response);
    }
}

