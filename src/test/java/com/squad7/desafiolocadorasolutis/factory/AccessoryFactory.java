package com.squad7.desafiolocadorasolutis.factory;

import com.squad7.desafiolocadorasolutis.model.Accessory;
import org.instancio.Instancio;
import org.instancio.Model;

import java.util.ArrayList;
import java.util.List;

import static org.instancio.Select.field;

public class AccessoryFactory {

    private static final Model<Accessory> ACCESSORY =
            Instancio.of(Accessory.class)
                    .set(field(Accessory::getCars), new ArrayList<>())
                    .toModel();


    public static Accessory createValidAccessory() {
        return Instancio.of(ACCESSORY)
                .create();
    }

    public static List<Accessory> withSize(int size) {
        return Instancio.ofList(ACCESSORY)
                .size(size)
                .create();
    }
}
