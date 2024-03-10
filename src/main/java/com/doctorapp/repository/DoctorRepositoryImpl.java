package com.doctorapp.repository;

import com.doctorapp.model.Doctor;
import com.doctorapp.util.DoctorDb;
import com.doctorapp.util.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepositoryImpl implements IDoctorRepository {
    @Override
    public void addDoctor(Doctor doctor) {

        try (Connection connection = DoctorDb.openConnection();
             PreparedStatement statement = connection.prepareStatement(Queries.INSERTQUERY);
        ) {
            statement.setString(1, doctor.getDoctorName());
            statement.setString(2, doctor.getSpeciality());
            statement.setInt(3, doctor.getExperience());
            statement.setInt(4, doctor.getRatings());
            statement.setDouble(5, doctor.getFees());

            boolean result = statement.execute();
            System.out.println("one row inserted" + !result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDoctor(int doctorId, double fees) {
        try (Connection connection = DoctorDb.openConnection();
             PreparedStatement statement = connection.prepareStatement(Queries.UPDATEQUERY);
        ) {
            statement.setInt(2, doctorId);
            statement.setDouble(1, fees);
            int result = statement.executeUpdate();
            System.out.println("one row updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDoctor(int doctorId) {
        try (Connection connection = DoctorDb.openConnection();
             PreparedStatement statement = connection.prepareStatement(Queries.DELETEQUERY);
        ) {
            statement.setInt(1, doctorId);
            int result = statement.executeUpdate();
            System.out.println("one row updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Doctor findBYId(int doctorId) {
        List<Doctor> doctors = new ArrayList<>();
        try (Connection connection = DoctorDb.openConnection();
             PreparedStatement statement = connection.prepareStatement(Queries.FINDBYID)) {
            statement.setInt(1, doctorId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String doctorName = resultSet.getString("doctor_name");
                    String speciality = resultSet.getString("speciality");
                    int experience = resultSet.getInt("experience");
                    int ratings = resultSet.getInt("ratings");
                    double fees = resultSet.getDouble("fees");

                    Doctor doctor = new Doctor(doctorId, doctorName, speciality, fees, ratings, experience);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Doctor> findAll() {
        List<Doctor> doctors = new ArrayList<>();
        try (Connection connection = DoctorDb.openConnection();
             PreparedStatement statement = connection.prepareStatement(Queries.FINDALLQUERY);
             ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                String doctorName = resultSet.getString("doctor_name");
                String speciality = resultSet.getString("speciality");
                int experience = resultSet.getInt("experience");
                int ratings = resultSet.getInt("ratings");
                int doctorId = resultSet.getInt("doctor_id");
                double fees = resultSet.getDouble("fees");

                Doctor doctor = new Doctor(doctorId, doctorName, speciality, fees, ratings, experience);
                // add doctor to list(inside loop)
                doctors.add(doctor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return doctors;
    }

    @Override
    public List<Doctor> findBySpeciality(String speciality) {
        List<Doctor> doctors = new ArrayList<>();
        try (Connection connection = DoctorDb.openConnection();
             PreparedStatement statement = connection.prepareStatement(Queries.FINDBYSPECIALITY);

        ) {
            statement.setString(1, speciality);
            try (ResultSet resultSet = statement.executeQuery();) {
                while (resultSet.next()) {
                    String doctorName = resultSet.getString("doctor_name");
                    int experience = resultSet.getInt("experience");
                    int ratings = resultSet.getInt("ratings");
                    int doctorId = resultSet.getInt("doctor_id");
                    double fees = resultSet.getDouble("fees");
                    Doctor doctor = new Doctor();
                    doctor.setDoctorName(doctorName);
                    doctor.setSpeciality(speciality);
                    doctor.setExperience(experience);
                    doctor.setRatings(ratings);
                    doctor.setFees(fees);
                    doctor.setDoctorId(doctorId);


                    doctors.add(doctor);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctors;
    }

    @Override
    public List<Doctor> findBySpecialityAndExp(String speciality, int experience) {
        List<Doctor> doctors = new ArrayList<>();
        try (Connection connection = DoctorDb.openConnection();
             PreparedStatement statement = connection.prepareStatement(Queries.FINDBYSPECANDEXP)) {
            statement.setString(1, speciality);
            statement.setInt(2, experience);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int doctorId = resultSet.getInt("doctor_id");
                    String doctorName = resultSet.getString("doctor_name");
                    int ratings = resultSet.getInt("ratings");
                    double fees = resultSet.getDouble("fees");

                    Doctor doctor = new Doctor(doctorId, doctorName, speciality, fees, ratings, experience);
                    doctors.add(doctor);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctors;
    }

    @Override
    public List<Doctor> findBySpecialityAndLessFees(String speciality, double fees) {
        List<Doctor> doctors = new ArrayList<>();
        try (Connection connection = DoctorDb.openConnection();
             PreparedStatement statement = connection.prepareStatement(Queries.FINDBYSPECANDFEES);
        ) {
            statement.setString(1, speciality);
            statement.setDouble(2, fees);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int doctorId = resultSet.getInt("doctor_id");
                    String doctorName = resultSet.getString("doctor_name");
                    int experience = resultSet.getInt("experience");
                    int ratings = resultSet.getInt("ratings");

                    Doctor doctor = new Doctor(doctorId, doctorName, speciality, fees, ratings, experience);
                    doctors.add(doctor);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctors;
        }

    @Override
    public List<Doctor> findBySpecialityAndRatings(String speciality, int ratings) {
        List<Doctor> doctors = new ArrayList<>();
        try (Connection connection = DoctorDb.openConnection();
             PreparedStatement statement = connection.prepareStatement(Queries.FINDBYSPECANDRATINGS);
        ){
            statement.setString(1, speciality);
            statement.setInt(2, ratings);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int doctorId = resultSet.getInt("doctor_id");
                    String doctorName = resultSet.getString("doctor_name");
                    int experience = resultSet.getInt("experience");
                    double fees = resultSet.getDouble("fees");

                    Doctor doctor = new Doctor(doctorId, doctorName, speciality, fees, ratings, experience);
                    doctors.add(doctor);
                }
            }

                }catch (Exception e) {
            e.printStackTrace();
        }
        return doctors;
    }

    @Override
    public List<Doctor> findBySpecialityAndNameContains(String speciality, String doctorName) {
        List<Doctor> doctors = new ArrayList<>();
        try (Connection connection = DoctorDb.openConnection();
             PreparedStatement statement =
                     connection.prepareStatement(Queries.FINDBYSPECANDNAME);
        ) {
            statement.setString(1, speciality);
            statement.setString(2, "%" + doctorName + "%");
            try (ResultSet resultSet = statement.executeQuery();) {
                while (resultSet.next()) {
                    Doctor doctor = new Doctor();
                    doctor.setDoctorName(resultSet.getString("doctor_name"));
                    doctor.setSpeciality(resultSet.getString("speciality"));
                    doctor.setExperience(resultSet.getInt("experience"));
                    doctor.setRatings(resultSet.getInt("ratings"));
                    doctor.setFees(resultSet.getDouble("fees"));
                    doctor.setDoctorId(resultSet.getInt("doctor_id"));
                    doctors.add(doctor);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctors;

    }
}
