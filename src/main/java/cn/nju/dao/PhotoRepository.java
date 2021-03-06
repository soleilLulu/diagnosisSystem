package cn.nju.dao;

import cn.nju.model.Photo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhotoRepository extends CrudRepository<Photo, String> {

    /**
     * 获得编号为did的医生的所有照片
     * @param did
     * @return
     */
    List<Photo> findAllByDidOrderByTime(String did);

    /**
     * 获得编号为did的医生的所有未回复照片
     * @param did
     * @return
     */
    List<Photo> findAllByDidAndTidIsNull(String did);

    /**
     * 获得所有未回复的照片
     * @return
     */
    List<Photo> findAllByTidIsNull();

    /**
     * 获得编号为did的医生的所有的已经回复的照片
     * @param did
     * @return
     */
    List<Photo> findAllByDidAndTidIsNotNull(String did);

    /**
     * 获得已经回复的所有照片
     * @return
     */
    List<Photo> findAllByTidIsNotNull();

    /**
     * 根据分类标签获得打上该标签的所有照片
     * @param tid
     * @return
     */
    List<Photo> findAllByTidIsContaining(String tid);

    /**
     * 计算当前标签下的照片数量
     * @param tid
     * @return
     */
    int countAllByTid(String tid);
}
