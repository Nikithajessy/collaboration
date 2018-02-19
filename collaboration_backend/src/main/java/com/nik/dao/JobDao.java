package com.nik.dao;

import java.util.List;

import com.nik.model.Job;

public interface JobDao {
void addJob(Job job);
List<Job> getAllJobs();
Job getJob(int id);
}
