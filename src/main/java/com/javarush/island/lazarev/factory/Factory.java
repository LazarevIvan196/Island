package com.javarush.island.lazarev.factory;

import com.javarush.island.lazarev.entities.Nature;
import com.javarush.island.lazarev.repository.NatureParameters;


public interface Factory {
  Nature create (NatureParameters parameters);
}
