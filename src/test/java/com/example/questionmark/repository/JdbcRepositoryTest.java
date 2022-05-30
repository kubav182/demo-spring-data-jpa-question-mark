package com.example.questionmark.repository;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"test", "jdbc"})
public class JdbcRepositoryTest extends AbstractRepositoryTest {

}
