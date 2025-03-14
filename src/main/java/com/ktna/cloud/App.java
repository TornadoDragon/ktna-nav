package com.ktna.cloud;

import org.noear.solon.Solon;
import org.noear.solon.annotation.SolonMain;

import java.sql.SQLException;

@SolonMain
public class App {
    public static void main(String[] args) throws SQLException {
        Solon.start(App.class, args);
    }
}