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
import co.edu.uniandes.csw.mypets.entities.AnimalEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.ArrayList;
import co.edu.uniandes.csw.mypets.entities.AnimalEntity;
import co.edu.uniandes.csw.mypets.entities.AnimalEntity;
import co.edu.uniandes.csw.mypets.entities.BreedEntity;
import co.edu.uniandes.csw.mypets.entities.PhotoAlbumEntity;

import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement
public class AnimalDetailDTO extends AnimalDTO{



@PodamExclude
private AnimalDTO father;
@PodamExclude
private AnimalDTO mother;
@PodamExclude
private BreedDTO breed;
@PodamExclude
private List<PhotoAlbumDTO> photoAlbum;


public AnimalDetailDTO() {
        super();
    }

 public AnimalDetailDTO(AnimalEntity entity) {
  super(entity); 
 List<PhotoAlbumDTO> lphotoAlbum = new ArrayList<>();
 if (entity.getFather()!=null){
 this.father = new AnimalDTO(entity.getFather());
 }
 if (entity.getMother()!=null){
 this.mother = new AnimalDTO(entity.getMother());
 }
 if (entity.getBreed()!=null){
 this.breed = new BreedDTO(entity.getBreed());
 }
 if (entity.getPhotoAlbum()!=null){
  for(PhotoAlbumEntity ent : entity.getPhotoAlbum()){
  lphotoAlbum.add(new PhotoAlbumDTO(ent));
 }
 this.photoAlbum = lphotoAlbum;
 }
 }

   public AnimalEntity toEntity() {
  AnimalEntity entity = (AnimalEntity) super.toEntity();
  List<PhotoAlbumEntity> lphotoAlbum = new ArrayList<>();
   if (this.getFather()!=null){
          entity.setFather(this.getFather().toEntity());
          }
   if (this.getMother()!=null){
          entity.setMother(this.getMother().toEntity());
          }
   if (this.getBreed()!=null){
          entity.setBreed(this.getBreed().toEntity());
          }
  if (this.getPhotoAlbum()!=null){
    for(PhotoAlbumDTO dto : this.getPhotoAlbum()){
  lphotoAlbum.add(dto.toEntity());
      
   }       
    entity.setPhotoAlbum(lphotoAlbum);  
   }
  return entity;
  }

public AnimalDTO getFather() {
        return father;
    }
 
    public void setFather(AnimalDTO father) {
        this.father = father;
    }
public AnimalDTO getMother() {
        return mother;
    }
 
    public void setMother(AnimalDTO mother) {
        this.mother = mother;
    }
public BreedDTO getBreed() {
        return breed;
    }
 
    public void setBreed(BreedDTO breed) {
        this.breed = breed;
    }
 public List<PhotoAlbumDTO> getPhotoAlbum() {
        return photoAlbum;
    }
 
    public void setPhotoAlbum(List<PhotoAlbumDTO> photoAlbum) {
        this.photoAlbum = photoAlbum;
    }



}
