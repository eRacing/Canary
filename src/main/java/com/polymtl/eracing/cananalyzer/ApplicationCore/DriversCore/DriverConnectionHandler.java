package com.polymtl.eracing.cananalyzer.ApplicationCore.DriversCore;

public class DriverConnectionHandler {
    private AbsDriver f_driver;

    public DriverConnectionHandler(AbsDriver driver) {
        f_driver = driver;
    }

    public void setDriver(AbsDriver driver) {
        f_driver = driver;
    }


}
