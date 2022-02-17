package com.example.myapplication;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetServices {

        private String Spno;

        public List<Services>getData(String Spno) {
            this.Spno = Spno;


            List<Services> data = new ArrayList<>();
            Database database = new Database();
            database.ConnectDB();
            ResultSet resultSet = database.RunSearch("select * from Services_type where Type_service='"+Spno);
            try {
                while (resultSet.next())           //not if , but use while To More than one recipe
                {
                    Services recipes = new Services();
                    recipes.setService_No(resultSet.getString(1));
                    recipes.setService_Name(resultSet.getString(2));
                    recipes.setService_img(resultSet.getString(3));
                    recipes.setRevelant_No(resultSet.getString(4));

                    data.add(recipes);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return data;
        }
    }
