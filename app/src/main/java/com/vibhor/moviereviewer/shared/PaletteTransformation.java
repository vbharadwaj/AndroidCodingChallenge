package com.vibhor.moviereviewer.shared;

import android.graphics.*;
import android.support.v7.graphics.*;

import com.squareup.picasso.*;

import java.util.*;

public class PaletteTransformation implements Transformation {

    private static final PaletteTransformation INSTANCE = new PaletteTransformation();
    private static final Map<Bitmap, Palette> CACHE = new WeakHashMap<>();

    private PaletteTransformation() {
    }

    public static PaletteTransformation instance() {
        return INSTANCE;
    }

    public static Palette getPalette(Bitmap bitmap) {
        return CACHE.get(bitmap);
    }

    @Override
    public Bitmap transform(Bitmap source) {
        Palette palette = new Palette.Builder(source)
                .generate();
        CACHE.put(source, palette);
        return source;
    }

    @Override
    public String key() {
        return "";
    }
}