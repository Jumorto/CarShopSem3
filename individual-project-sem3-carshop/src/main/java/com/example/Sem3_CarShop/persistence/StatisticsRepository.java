package com.example.Sem3_CarShop.persistence;

import com.example.Sem3_CarShop.persistence.entity.AddEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatisticsRepository extends JpaRepository<AddEntity, Long> {

    @Query(value = "select brand_Name, br from \n" +
            " (select adv.brand, b.brand_Name, count(*) br\n" +
            " FROM Advertisement adv \n" +
            "            inner join Brands b On b.id = adv.brand \n" +
            "            group by adv.brand \n" +
            "            order by br desc limit 5) as x \n" +
            "union\n" +
            "select \"Others\", sum(br) from\n" +
            " (SELECT count(*)\n" +
            "  br\n" +
            " FROM Advertisement adv \n" +
            "        group by adv.brand \n" +
            "        order by br desc limit 10000000000  offset 5) as x ", nativeQuery = true)
    List<Object[]> getTop5Brands();

    @Query(value ="SELECT  \"0 - 100000 km\",  count(kilometers) br FROM Advertisement adv  where kilometers between 0 and 100000  \n" +
            " union\n" +
            " SELECT  \"100001 - 150000 km\", count(kilometers) br FROM Advertisement adv  where kilometers between 100001 and 150000  \n" +
            " union\n" +
            " SELECT   \"150001 - 200000 km\", count(kilometers) br FROM Advertisement adv  where kilometers between 150001 and 200000  \n" +
            " union\n" +
            " SELECT   \"200000 km\",  count(kilometers) br FROM Advertisement adv  where kilometers > 200000  ", nativeQuery = true)
    List<Object[]> advertsByKilometers();

    @Query(value = "Select k1, brand_Name, br from (\n" +
            " SELECT  \"0 - 100000\" k1,  count(kilometers) br,  b.brand_Name, adv.brand FROM Advertisement adv    inner join Brands b On b.id = adv.brand  where kilometers between 0 and 100000   group by brand\n" +
            " union\n" +
            " SELECT  \"100001 - 150000\" k1, count(kilometers) br, b.brand_Name, adv.brand   FROM Advertisement adv     inner join Brands b On b.id = adv.brand where kilometers between 100001 and 150000    group by brand\n" +
            " union\n" +
            " SELECT   \"150001 - 200000\" k1, count(kilometers) br, b.brand_Name, adv.brand FROM Advertisement adv    inner join Brands b On b.id = adv.brand  where kilometers between 150001 and 200000   group by brand\n" +
            " union\n" +
            " SELECT   \"200000\" k1,  count(kilometers) br, b.brand_Name , adv.brand FROM Advertisement adv    inner join Brands b On b.id = adv.brand  where kilometers > 200000     group by brand\n" +
            " ) as ch2 \n" +
            " where brand = :brand\n" +
            " order by  ch2.brand_Name",nativeQuery = true)
    List<Object[]> brandsByKilometers(@Param("brand") Long brand);
}
