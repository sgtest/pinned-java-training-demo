package org.javaboy.test.model;

import java.util.Date;

public class User{

                /**
                * 
                */
                private Integer id;
                /**
                * 
                */
                private String username;
                /**
                * 
                */
                private String password;
                /**
                * 
                */
                private Boolean enabled;
                /**
                * 
                */
                private Boolean locked;
                public Integer getId(){
                return id;
                }
                public void setId(Integer id){
                this.id=id;
                }
                public String getUsername(){
                    return username;
                }
                public void setUsername(String username){
                    this.username=username;
                }
                public String getPassword(){
                    return password;
                }
                public void setPassword(String password){
                    this.password=password;
                }
                public Boolean getEnabled(){
                return enabled;
                }
                public void setEnabled(Boolean enabled){
                this.enabled=enabled;
                }
                public Boolean getLocked(){
                return locked;
                }
                public void setLocked(Boolean locked){
                this.locked=locked;
                }
}