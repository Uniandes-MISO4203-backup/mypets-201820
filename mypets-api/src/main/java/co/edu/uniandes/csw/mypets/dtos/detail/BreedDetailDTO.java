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
package co.edu.uniandes.csw.mypets.dtos.detail;

import co.edu.uniandes.csw.mypets.dtos.minimum.*;
import co.edu.uniandes.csw.mypets.entities.BreedEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.ArrayList;
import co.edu.uniandes.csw.mypets.entities.AnimalEntity;
import co.edu.uniandes.csw.mypets.entities.SpecieEntity;

import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement
public class BreedDetailDTO extends BreedDTO{



@PodamExclude
private List<AnimalDTO> animal;
@PodamExclude
private SpecieDTO specie;


public BreedDetailDTO() {
        super();
    }

 public BreedDetailDTO(BreedEntity entity) {
  super(entity); 
 List<AnimalDTO> lanimal = new ArrayList<>();
 if (entity.getAnimal()!=null){
  for(AnimalEntity ent : entity.getAnimal()){
  lanimal.add(new AnimalDTO(ent));
 }
 this.animal = lanimal;
 }
 if (entity.getSpecie()!=null){
 this.specie = new SpecieDTO(entity.getSpecie());
 }
 }

   public BreedEntity toEntity() {
  BreedEntity entity = (BreedEntity) super.toEntity();
  List<AnimalEntity> lanimal = new ArrayList<>();
  if (this.getAnimal()!=null){
    for(AnimalDTO dto : this.getAnimal()){
  lanimal.add(dto.toEntity());
      
   }       
    entity.setAnimal(lanimal);  
   }
   if (this.getSpecie()!=null){
          entity.setSpecie(this.getSpecie().toEntity());
          }
  return entity;
  }

 public List<AnimalDTO> getAnimal() {
        return animal;
    }
 
    public void setAnimal(List<AnimalDTO> animal) {
        this.animal = animal;
    }

public SpecieDTO getSpecie() {
        return specie;
    }
 
    public void setSpecie(SpecieDTO specie) {
        this.specie = specie;
    }


}
