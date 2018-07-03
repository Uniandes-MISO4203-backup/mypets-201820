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
package co.edu.uniandes.csw.mypets.ejbs;

import co.edu.uniandes.csw.mypets.api.ISpecieLogic;
import co.edu.uniandes.csw.mypets.entities.SpecieEntity;
import co.edu.uniandes.csw.mypets.persistence.SpeciePersistence;
import co.edu.uniandes.csw.mypets.entities.BreedEntity;
import co.edu.uniandes.csw.mypets.api.IBreedLogic;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;



/**
 * @generated
 */
@Stateless
public class SpecieLogic implements ISpecieLogic {

    @Inject private SpeciePersistence persistence;


    @Inject private IBreedLogic breedLogic;

    /**
     * Obtiene el número de registros de Specie.
     *
     * @return Número de registros de Specie.
     * @generated
     */
    public int countSpecies() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de Specie.
     *
     * @return Colección de objetos de SpecieEntity.
     * @generated
     */
    @Override
    public List<SpecieEntity> getSpecies() {
        return persistence.findAll();
    }

    /**
     * Obtiene la lista de los registros de Specie indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @return Colección de objetos de SpecieEntity.
     * @generated
     */
    @Override
    public List<SpecieEntity> getSpecies(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    /**
     * Obtiene los datos de una instancia de Specie a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de SpecieEntity con los datos del Specie consultado.
     * @generated
     */
    public SpecieEntity getSpecie(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un Specie en la base de datos.
     *
     * @param entity Objeto de SpecieEntity con los datos nuevos
     * @return Objeto de SpecieEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public SpecieEntity createSpecie(SpecieEntity entity) {

        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Specie.
     *
     * @param entity Instancia de SpecieEntity con los nuevos datos.
     * @return Instancia de SpecieEntity con los datos actualizados.
     * @generated
     */
    @Override
    public SpecieEntity updateSpecie(SpecieEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Specie de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @Override
    public void deleteSpecie(Long id) {
        persistence.delete(id);
    }
  

    /**
     * Obtiene una colección de instancias de BreedEntity asociadas a una
     * instancia de Specie
     *
     * @param specieId Identificador de la instancia de Specie
     * @return Colección de instancias de BreedEntity asociadas a la instancia de Specie
     * @generated
     */
    @Override
    public List<BreedEntity> listBreed(Long specieId) {
        return getSpecie(specieId).getBreed();
    }

    /**
     * Obtiene una instancia de BreedEntity asociada a una instancia de Specie
     *
     * @param specieId Identificador de la instancia de Specie
     * @param breedId Identificador de la instancia de Breed
     * @generated
     */
    @Override
    public BreedEntity getBreed(Long specieId, Long breedId) {
        List<BreedEntity> list = getSpecie(specieId).getBreed();
        BreedEntity breedEntity = new BreedEntity();
        breedEntity.setId(breedId);
        int index = list.indexOf(breedEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Breed existente a un Specie
     *
     * @param specieId Identificador de la instancia de Specie
     * @param breedId Identificador de la instancia de Breed
     * @return Instancia de BreedEntity que fue asociada a Specie
     * @generated
     */
    @Override
    public BreedEntity addBreed(Long specieId, Long breedId) {
        SpecieEntity specieEntity = getSpecie(specieId);
        BreedEntity breedEntity = breedLogic.getBreed(breedId);
        breedEntity.setSpecie(specieEntity);
        return breedEntity;
    }

    /**
     * Remplaza las instancias de Breed asociadas a una instancia de Specie
     *
     * @param specieId Identificador de la instancia de Specie
     * @param list Colección de instancias de BreedEntity a asociar a instancia de Specie
     * @return Nueva colección de BreedEntity asociada a la instancia de Specie
     * @generated
     */
    @Override
    public List<BreedEntity> replaceBreed(Long specieId, List<BreedEntity> list) {
        SpecieEntity specieEntity = getSpecie(specieId);
        List<BreedEntity> breedList = breedLogic.getBreeds();
        for (BreedEntity breed : breedList) {
            if (list.contains(breed)) {
                breed.setSpecie(specieEntity);
            } else {
                if (breed.getSpecie() != null && breed.getSpecie().equals(specieEntity)) {
                    breed.setSpecie(null);
                }
            }
        }
        specieEntity.setBreed(list);
        return specieEntity.getBreed();
    }

    /**
     * Desasocia un Breed existente de un Specie existente
     *
     * @param specieId Identificador de la instancia de Specie
     * @param breedId Identificador de la instancia de Breed
     * @generated
     */
    @Override
    public void removeBreed(Long specieId, Long breedId) {
        BreedEntity entity = breedLogic.getBreed(breedId);
        entity.setSpecie(null);
    }
}
