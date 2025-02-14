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
package co.edu.uniandes.csw.mypets.test.persistence;
import co.edu.uniandes.csw.mypets.entities.AnimalEntity;
import co.edu.uniandes.csw.mypets.entities.PhotoAlbumEntity;
import co.edu.uniandes.csw.mypets.persistence.PhotoAlbumPersistence;
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
public class PhotoAlbumPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PhotoAlbumEntity.class.getPackage())
                .addPackage(PhotoAlbumPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    AnimalEntity fatherEntity;

    /**
     * @generated
     */
    @Inject
    private PhotoAlbumPersistence photoAlbumPersistence;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    UserTransaction utx;

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
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
        em.createQuery("delete from PhotoAlbumEntity").executeUpdate();
        em.createQuery("delete from AnimalEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<PhotoAlbumEntity> data = new ArrayList<PhotoAlbumEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
            fatherEntity = factory.manufacturePojo(AnimalEntity.class);
            fatherEntity.setId(1L);
            em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            PhotoAlbumEntity entity = factory.manufacturePojo(PhotoAlbumEntity.class);
            
            entity.setAnimal(fatherEntity);
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un PhotoAlbum.
     *
     * @generated
     */
    @Test
    public void createPhotoAlbumTest() {
		PodamFactory factory = new PodamFactoryImpl();
        PhotoAlbumEntity newEntity = factory.manufacturePojo(PhotoAlbumEntity.class);
        newEntity.setAnimal(fatherEntity);
        PhotoAlbumEntity result = photoAlbumPersistence.create(newEntity);

        Assert.assertNotNull(result);

        PhotoAlbumEntity entity = em.find(PhotoAlbumEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getImage(), entity.getImage());
    }

    /**
     * Prueba para consultar la lista de PhotoAlbums.
     *
     * @generated
     */
    @Test
    public void getPhotoAlbumsTest() {
        List<PhotoAlbumEntity> list = photoAlbumPersistence.findAll(null, null, fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (PhotoAlbumEntity ent : list) {
            boolean found = false;
            for (PhotoAlbumEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un PhotoAlbum.
     *
     * @generated
     */
    @Test
    public void getPhotoAlbumTest() {
        PhotoAlbumEntity entity = data.get(0);
        PhotoAlbumEntity newEntity = photoAlbumPersistence.find(entity.getAnimal().getId(), entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getImage(), newEntity.getImage());
    }

    /**
     * Prueba para eliminar un PhotoAlbum.
     *
     * @generated
     */
    @Test
    public void deletePhotoAlbumTest() {
        PhotoAlbumEntity entity = data.get(0);
        photoAlbumPersistence.delete(entity.getId());
        PhotoAlbumEntity deleted = em.find(PhotoAlbumEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un PhotoAlbum.
     *
     * @generated
     */
    @Test
    public void updatePhotoAlbumTest() {
        PhotoAlbumEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PhotoAlbumEntity newEntity = factory.manufacturePojo(PhotoAlbumEntity.class);

        newEntity.setId(entity.getId());

        photoAlbumPersistence.update(newEntity);

        PhotoAlbumEntity resp = em.find(PhotoAlbumEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getImage(), resp.getImage());
    }
}
