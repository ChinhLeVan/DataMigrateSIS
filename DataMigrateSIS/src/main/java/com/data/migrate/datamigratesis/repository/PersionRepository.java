package com.data.migrate.datamigratesis.repository;

import com.data.migrate.datamigratesis.entity.Persion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersionRepository extends JpaRepository<Persion, Integer> {
    // Bạn có thể thêm các phương thức truy vấn tùy chỉnh ở đây nếu cần
}
