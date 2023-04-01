package stepdefinitions.dbstepdefinitions;

import io.cucumber.java.en.Given;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static stepdefinitions.uistepdefinitions.CreateRoomUI_StepDefinitions.number;


public class RoomDB_StepDefinitions {

    @Given("Olusan odayi room_number ile dogrula")
    public void olusan_odayi_room_number_ile_dogrula() throws SQLException {
        //Database e baglandik(Connection olusturduk)
        //javadan gelen DriverManager.getConnection methodunu kullandık
        Connection connection = DriverManager.getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2", "select_user", "Medunna_pass_@6");
        Statement statement = connection.createStatement();
        //bu connection kullanarak Query cagirmak icin statement olusturduk
        //table olusturma ve table den data cagirma

        ResultSet resultSet = statement.executeQuery("SELECT * FROM room ORDER BY created_date DESC");

        List<Integer> roomNumberList = new ArrayList<>();
        /*
            tablodaki satirlarin basinda pointer bulunur. resultSet.next() methodu altta bir satir daha varsa
            pointer i alt satira alir.boolean return eder
            ->sutun sayilari 1 den baslar
         */
        while (resultSet.next()) {

            roomNumberList.add(resultSet.getInt(2));//sutun ismide yazilabilir(daha garanti)

        }
        assertTrue(roomNumberList.contains(number));

    }
}
