package com.example.services.beans.addrecipe;

import com.example.services.beans.steprecipe.Step;

import java.io.Serializable;
import java.util.List;

public class RecipeJSON implements Serializable {
    private String label;
    private Integer is_private = 0;
    private String image;
    private Integer id_user;
    private Integer id_recipe_type;
    private List<Step> steps;

    public RecipeJSON(String label, Boolean is_private, String image, Integer id_user, Integer id_recipe_type, List<Step> steps) {
        this.label = label;
        if(is_private){
            this.is_private = 1;
        }
        this.image = image;
        this.id_user = id_user;
        this.id_recipe_type = id_recipe_type;
        this.steps = steps;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getIs_private() {
        return is_private;
    }

    public void setIs_private(Integer is_private) {
        this.is_private = is_private;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId_recipe_type() {
        return id_recipe_type;
    }

    public void setId_recipe_type(Integer id_recipe_type) {
        this.id_recipe_type = id_recipe_type;
    }
}
