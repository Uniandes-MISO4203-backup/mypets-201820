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

import co.edu.uniandes.csw.mypets.entities.AnimalEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @generated
 */
@XmlRootElement
public class AnimalDTO  implements Serializable{


private Long id;
private String name;
private String birthdate;
private String gender;
private String color;

 

    public AnimalDTO() {
    }


public AnimalDTO(AnimalEntity entity) {
   if (entity!=null){
    this.name=entity.getName();
    this.birthdate=entity.getBirthdate();
    this.gender=entity.getGender();
    this.color=entity.getColor();
       }
    }
    


 public AnimalEntity toEntity() {
        AnimalEntity entity = new AnimalEntity();
        entity.setName(this.getName());
        entity.setBirthdate(this.getBirthdate());
        entity.setGender(this.getGender());
        entity.setColor(this.getColor());
         return entity;
       }
       


    
    public  Long getId() {
        return id;
    }
  
    public void setId( Long id) {
        this.id = id;
    }

    
    public  String getName() {
        return name;
    }
  
    public void setName( String name) {
        this.name = name;
    }

    
    public  String getBirthdate() {
        return birthdate;
    }
  
    public void setBirthdate( String birthdate) {
        this.birthdate = birthdate;
    }

    
    public  String getGender() {
        return gender;
    }
  
    public void setGender( String gender) {
        this.gender = gender;
    }

    
    public  String getColor() {
        return color;
    }
  
    public void setColor( String color) {
        this.color = color;
    }

}
