package com.example.mscatalogo.repository;

import com.example.mscatalogo.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepostory extends JpaRepository<Producto, Integer> {
}
