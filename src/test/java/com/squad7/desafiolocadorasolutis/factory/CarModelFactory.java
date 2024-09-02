package com.squad7.desafiolocadorasolutis.factory;

import com.squad7.desafiolocadorasolutis.model.CarModel;
import org.instancio.Instancio;
import org.instancio.Model;

public class CarModelFactory {
    private static final Model<CarModel> CAR_MODEL =
            Instancio.of(CarModel.class)
                    .toModel();


    public static CarModel createValidCarModel() {
        return Instancio.of(CAR_MODEL)
                .create();
    }

}
