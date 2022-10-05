package com.kasimkartal866.carstore.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RoomExecutor {
    private static RoomExecutor instance;
    private final Dao dao;


    private RoomExecutor (Context context) {
        dao = MyDatabase.getMyDatabase((Context) context).dao();

    }
    public static RoomExecutor getInstance(Context context) {
        if(instance == null)
            instance = new RoomExecutor(context);
        return instance;
    }
    public User checkUserPass() {
        User user = null;
        CheckUserPass_Async checkUserPassAsync = new CheckUserPass_Async();
        try {
            user = checkUserPassAsync.execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }
    public void addUser(User user) {
        if (user != null) {
            try {
                new AddUsers_Async().execute(user).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void addCar(Car car) {
        if (car != null) {
            new AddCar_Async(car).execute();
        }
    }
    public List<Car> getCars() {
        List<Car> cars = new ArrayList<>();
        try {
            cars = new GetCars_Async().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return cars;
    }
    public List<Car> getCarsByUser(int userId) {
        List<Car> cars = new ArrayList<>();
        try {
            if (userId == -1)
                cars = new GetCars_Async().execute().get();
            else
                cars = new GetMyCars_Async().execute(userId).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return cars;
    }
    //**********************************************************************************************

    private class CheckUserPass_Async extends AsyncTask<Void, Void, User> {
        @Override
        protected User doInBackground(Void... voids) {
            return dao.checkUserPass("email","password");
        }
    }

    private class Car_Async extends AsyncTask<Void, Void, List<User>> {
        @Override
        protected List<User> doInBackground(Void... voids) {
            return dao.getAllUsers();
        }
    }

    private class AddUsers_Async extends AsyncTask<User, Void, Void> {
        @Override
        protected Void doInBackground(User... users) {
            dao.addUser(users[0]);
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class AddCar_Async extends AsyncTask<Car, Void, Void> {
        public AddCar_Async(Car car) {
            this.car = car;
        }
        Car car;
        @Override
        protected Void doInBackground(Car... cars) {
            dao.addCar(car);
            return null;
        }
    }
    @SuppressLint("StaticFieldLeak")
    private class GetCars_Async extends AsyncTask<Void, Void, List<Car>> {
        @Override
        protected List<Car> doInBackground(Void... voids) {
            return dao.getAllCars();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetMyCars_Async extends AsyncTask<Integer, Void, List<Car>> {
        @Override
        protected List<Car> doInBackground(Integer... userIds) {
            return dao.getMyCars(userIds[0]);
        }
    }

}
