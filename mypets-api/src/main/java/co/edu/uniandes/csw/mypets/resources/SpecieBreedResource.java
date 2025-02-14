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
package co.edu.uniandes.csw.mypets.resources;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.mypets.api.ISpecieLogic;
import co.edu.uniandes.csw.mypets.dtos.detail.BreedDetailDTO;
import co.edu.uniandes.csw.mypets.entities.BreedEntity;
import java.util.ArrayList;
/**
 * URI: species/{speciesId: \\d+}/breed
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SpecieBreedResource {

    @Inject private ISpecieLogic specieLogic;
    @Context private HttpServletResponse response;

    /**
     * Convierte una lista de BreedEntity a una lista de BreedDetailDTO.
     *
     * @param entityList Lista de BreedEntity a convertir.
     * @return Lista de BreedDetailDTO convertida.
     * @generated
     */
    private List<BreedDetailDTO> breedListEntity2DTO(List<BreedEntity> entityList){
        List<BreedDetailDTO> list = new ArrayList<>();
        for (BreedEntity entity : entityList) {
            list.add(new BreedDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de BreedDetailDTO a una lista de BreedEntity.
     *
     * @param dtos Lista de BreedDetailDTO a convertir.
     * @return Lista de BreedEntity convertida.
     * @generated
     */
    private List<BreedEntity> breedListDTO2Entity(List<BreedDetailDTO> dtos){
        List<BreedEntity> list = new ArrayList<>();
        for (BreedDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     * Obtiene una colección de instancias de BreedDetailDTO asociadas a una
     * instancia de Specie
     *
     * @param speciesId Identificador de la instancia de Specie
     * @return Colección de instancias de BreedDetailDTO asociadas a la instancia de Specie
     * @generated
     */
    @GET
    public List<BreedDetailDTO> listBreed(@PathParam("speciesId") Long speciesId) {
        return breedListEntity2DTO(specieLogic.listBreed(speciesId));
    }

    /**
     * Obtiene una instancia de Breed asociada a una instancia de Specie
     *
     * @param speciesId Identificador de la instancia de Specie
     * @param breedId Identificador de la instancia de Breed
     * @generated
     */
    @GET
    @Path("{breedId: \\d+}")
    public BreedDetailDTO getBreed(@PathParam("speciesId") Long speciesId, @PathParam("breedId") Long breedId) {
        return new BreedDetailDTO(specieLogic.getBreed(speciesId, breedId));
    }

    /**
     * Asocia un Breed existente a un Specie
     *
     * @param speciesId Identificador de la instancia de Specie
     * @param breedId Identificador de la instancia de Breed
     * @return Instancia de BreedDetailDTO que fue asociada a Specie
     * @generated
     */
    @POST
    @Path("{breedId: \\d+}")
    public BreedDetailDTO addBreed(@PathParam("speciesId") Long speciesId, @PathParam("breedId") Long breedId) {
        return new BreedDetailDTO(specieLogic.addBreed(speciesId, breedId));
    }

    /**
     * Remplaza las instancias de Breed asociadas a una instancia de Specie
     *
     * @param speciesId Identificador de la instancia de Specie
     * @param breeds Colección de instancias de BreedDTO a asociar a instancia de Specie
     * @return Nueva colección de BreedDTO asociada a la instancia de Specie
     * @generated
     */
    @PUT
    public List<BreedDetailDTO> replaceBreed(@PathParam("speciesId") Long speciesId, List<BreedDetailDTO> breeds) {
        return breedListEntity2DTO(specieLogic.replaceBreed(speciesId, breedListDTO2Entity(breeds)));
    }

    /**
     * Desasocia un Breed existente de un Specie existente
     *
     * @param speciesId Identificador de la instancia de Specie
     * @param breedId Identificador de la instancia de Breed
     * @generated
     */
    @DELETE
    @Path("{breedId: \\d+}")
    public void removeBreed(@PathParam("speciesId") Long speciesId, @PathParam("breedId") Long breedId) {
        specieLogic.removeBreed(speciesId, breedId);
    }

}
