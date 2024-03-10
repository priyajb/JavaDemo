package com.doctorapp.service;

import com.doctorapp.exception.DoctorNotFoundException;
import com.doctorapp.exception.IdNotFoundException;
import com.doctorapp.model.Doctor;
import com.doctorapp.repository.DoctorRepositoryImpl;
import com.doctorapp.repository.IDoctorRepository;

import java.util.Collections;
import java.util.List;

public class DoctorServiceImpl implements IDoctorService {
    private IDoctorRepository doctorRepository = new DoctorRepositoryImpl();

    @Override
    public void addDoctor(Doctor doctor) {

        doctorRepository.addDoctor(doctor);
    }

    @Override
    public void updateDoctor(int doctorId, double fees) {
        doctorRepository.updateDoctor(doctorId, fees);
    }

    @Override
    public void deleteDoctor(int doctorId) {
        doctorRepository.deleteDoctor(doctorId);
    }

    @Override
    public Doctor findBYId(int doctorId) throws IdNotFoundException {
        Doctor doctor = doctorRepository.findBYId(doctorId);
        if (doctor == null)
            throw new IdNotFoundException("invalid id");
        return doctor;
    }

    @Override
    public List<Doctor> findAll() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors;
    }

    @Override
    public List<Doctor> findBySpeciality(String speciality) throws DoctorNotFoundException {
        List<Doctor> doctors = doctorRepository.findBySpeciality(speciality);
        if (doctors.isEmpty())
            throw new DoctorNotFoundException("doctor with this speciality not found");
        Collections.sort(doctors, (d1, d2) -> d1.getDoctorName().compareTo(d2.getDoctorName()));
        return doctors;
    }

    @Override
    public List<Doctor> findBySpecialityAndExp(String speciality, int experience) throws DoctorNotFoundException {
        List<Doctor> doctors = doctorRepository
                .findBySpecialityAndExp(speciality, experience);
        if (doctors.isEmpty())
            throw new DoctorNotFoundException("doctor with this speciality and exp not found");
        Collections.sort(doctors,
                (d1, d2) -> ((Integer) (d2.getExperience())).compareTo(d1.getExperience()));
        return doctors;
    }

    @Override
    public List<Doctor> findBySpecialityAndLessFees(String speciality, double fees) throws DoctorNotFoundException {
        List<Doctor> doctors = doctorRepository
                .findBySpecialityAndLessFees(speciality, fees);
        if (doctors.isEmpty())
            throw new DoctorNotFoundException("doctor with this speciality and fees not found");
        Collections.sort(doctors,
                (d1, d2) -> ((Double) (d1.getFees())).compareTo(d2.getFees()));
        return doctors;
    }

    @Override
    public List<Doctor> findBySpecialityAndRatings(String speciality, int ratings) throws DoctorNotFoundException {
        List<Doctor> doctors = doctorRepository
                .findBySpecialityAndRatings(speciality, ratings);
        if (doctors.isEmpty())
            throw new DoctorNotFoundException("doctor with this speciality and ratings not found");
        Collections.sort(doctors,
                (d1, d2) -> ((Integer) (d1.getRatings())).compareTo(d1.getRatings()));
        return doctors;
    }

    @Override
    public List<Doctor> findBySpecialityAndNameContains(String speciality, String doctorName) throws DoctorNotFoundException {
        List<Doctor> doctors = doctorRepository
                .findBySpecialityAndNameContains(speciality, doctorName);
        if (doctors.isEmpty())
            throw new DoctorNotFoundException("doctor with this speciality and exp not found");
        Collections.sort(doctors,
                (d1, d2) -> (d2.getDoctorName().compareTo(d2.getDoctorName())));
        return doctors;
    }
}
