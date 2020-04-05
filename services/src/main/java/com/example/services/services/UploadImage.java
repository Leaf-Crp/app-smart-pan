package com.example.services.services;

import java.awt.image.BufferedImage;
import java.util.concurrent.CompletableFuture;

public class UploadImage {
    private String name;
    private BufferedImage image;

    //https://www.youtube.com/watch?v=TMnQJKtmOd4
    public UploadImage(BufferedImage imageRecipe, String nameRecipe) {
        image = imageRecipe;
        name = nameRecipe;
    }

}
