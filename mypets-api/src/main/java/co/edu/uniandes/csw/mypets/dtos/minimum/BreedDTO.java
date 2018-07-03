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
package co.edu.uniandes.csw.mypets.dtos.minimum;

import co.edu.uniandes.csw.mypets.entities.BreedEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @generated
 */
@XmlRootElement
public class BreedDTO  implements Serializable{


private Long id;
private String description;
private String name;
private String mood;
private String size;
private Integer lifeExpectancy;

 

    public BreedDTO() {
    }


public BreedDTO(BreedEntity entity) {
   if (entity!=null){
    this.description=entity.getDescription();
    this.name=entity.getName();
    this.mood=entity.getMood();
    this.size=entity.getSize();
    this.lifeExpectancy=entity.getLifeExpectancy();
       }
    }
    


 public BreedEntity toEntity() {
        BreedEntity entity = new BreedEntity();
        entity.setDescription(this.getDescription());
        entity.setName(this.getName());
        entity.setMood(this.getMood());
        entity.setSize(this.getSize());
        entity.setLifeExpectancy(this.getLifeExpectancy());
         return entity;
       }
       


    
    public  Long getId() {
        return id;
    }
  
    public void setId( Long id) {
        this.id = id;
    }

    
    public  String getDescription() {
        return description;
    }
  
    public void setDescription( String description) {
        this.description = description;
    }

    
    public  String getName() {
        return name;
    }
  
    public void setName( String name) {
        this.name = name;
    }

    
    public  String getMood() {
        return mood;
    }
  
    public void setMood( String mood) {
        this.mood = mood;
    }

    
    public  String getSize() {
        return size;
    }
  
    public void setSize( String size) {
        this.size = size;
    }

    
    public  Integer getLifeExpectancy() {
        return lifeExpectancy;
    }
  
    public void setLifeExpectancy( Integer lifeexpectancy) {
        this.lifeExpectancy = lifeexpectancy;
    }

}
