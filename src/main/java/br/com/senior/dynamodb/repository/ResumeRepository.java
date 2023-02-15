package br.com.senior.dynamodb.repository;

import java.util.Optional;
import java.util.UUID;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.senior.dynamodb.entity.Resume;
import br.com.senior.dynamodb.entity.ResumeKey;

@EnableScan
public interface ResumeRepository extends PagingAndSortingRepository<Resume, ResumeKey> {

    Optional<Resume> findByIdentifier(UUID identifier);
}
