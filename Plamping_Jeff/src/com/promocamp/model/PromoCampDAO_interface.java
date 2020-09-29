package com.promocamp.model;

import java.util.List;

public interface PromoCampDAO_interface {
    public void insert(PromoCampVO pcVO);

    public void insert(List<PromoCampVO> pcVOList);

    public void update(PromoCampVO pcVO);

    public List<PromoCampVO> queryByPro_no(String pc_prono);

    public void delete(PromoCampVO pcVO);

    public void delete(List<PromoCampVO> pcVOList);
}
