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
import co.edu.uniandes.csw.mypets.entities.SpecieEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.ArrayList;
import co.edu.uniandes.csw.mypets.entities.BreedEntity;

import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement
public class SpecieDetailDTO extends SpecieDTO{



@PodamExclude
private List<BreedDTO> breed;


public SpecieDetailDTO() {
        super();
    }

 public SpecieDetailDTO(SpecieEntity entity) {
  super(entity); 
 List<BreedDTO> lbreed = new ArrayList<>();
 if (entity.getBreed()!=null){
  for(BreedEntity ent : entity.getBreed()){
  lbreed.add(new BreedDTO(ent));
 }
 this.breed = lbreed;
 }
 }

   public SpecieEntity toEntity() {
  SpecieEntity entity = (SpecieEntity) super.toEntity();
  List<BreedEntity> lbreed = new ArrayList<>();
  if (this.getBreed()!=null){
    for(BreedDTO dto : this.getBreed()){
  lbreed.add(dto.toEntity());
      
   }       
    entity.setBreed(lbreed);  
   }
  return entity;
  }

 public List<BreedDTO> getBreed() {
        return breed;
    }
 
    public void setBreed(List<BreedDTO> breed) {
        this.breed = breed;
    }



}
