package com.example.Sem3_CarShop.persistence;

import com.example.Sem3_CarShop.persistence.entity.AddEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AddRepository extends JpaRepository<AddEntity, Long> {
    @Query("SELECT  adv.id, adv.idUserCreate, adv.name, adv.description, \n" +
            "       vt.vehicleType,  b.brandName,  e.engineType, g.gearboxType, adv.num_doors, adv.date_manufacture, adv.kilometers, adv.price, adv.date_publish, vt.id, b.id, e.id, g.id FROM AddEntity adv " +
            "inner join VehicleTypeEntity vt On vt.id = adv.vehicleType \n" +
            "        inner join BrandEntity b On b.id = adv.brand \n" +
            "        inner join EngineTypeEntity e On e.id = adv.engine_type \n" +
            "        inner join GearboxEntity g On g.id = adv.gearbox \n" +
            "WHERE  (:idUserCreate is null or adv.idUserCreate = :idUserCreate) \n" +
            //"and (:addName is null or adv.name LIKE \'%:addName%\') \n" +
            "and (:description is null or adv.description = :description) \n" +
            "and (:vehicleType is null or adv.vehicleType = :vehicleType) \n" +
            "and (:brand is null or adv.brand = :brand) \n" +
            "and (:engine_type is null or adv.engine_type = :engine_type) \n" +
            "and (:gearbox is null or adv.gearbox = :gearbox) \n" +
            "and (:num_doors is null or adv.num_doors = :num_doors) \n" +
            "and (:date_manufacture is null or adv.date_manufacture = :date_manufacture) \n" +
            "and (:kilometers is null or adv.kilometers = :kilometers) \n" +
            "and (:price is null or adv.price = :price) \n" +
            "and (:date_publish is null or adv.date_publish = :date_publish) \n" +
            "and (:priceMin is null or  adv.price >= :priceMin) \n"+
            "and (:priceMax is null or  adv.price <= :priceMax) \n")
    List<Object[]> findAdvertsByGiveParameters(@Param("idUserCreate") Long idUserCreate,
//                                             @Param("addName") String addName,
                                             @Param("description") String description,
                                             @Param("vehicleType") Integer vehicleType,
                                             @Param("brand") Integer brand,
                                             @Param("engine_type") Integer engine_type,
                                             @Param("gearbox") Integer gearbox,
                                             @Param("num_doors") Integer num_doors,
                                             @Param("date_manufacture") Date date_manufacture,
                                             @Param("kilometers") Long kilometers,
                                             @Param("price") Double price,
                                             @Param("date_publish") Date date_publish,
                                             @Param("priceMin") Double priceMin,
                                             @Param("priceMax") Double priceMax);

    @Query("SELECT  adv.id, adv.idUserCreate, adv.name, adv.description,  \n" +
            "        vt.vehicleType, b.brandName, e.engineType, g.gearboxType, adv.num_doors, adv.date_manufacture, adv.kilometers, adv.price, adv.date_publish, vt.id, b.id, e.id, g.id FROM AddEntity adv " +
            "inner join VehicleTypeEntity vt On vt.id = adv.vehicleType\n" +
            "        inner join BrandEntity b On b.id = adv.brand\n" +
            "        inner join EngineTypeEntity e On e.id = adv.engine_type\n" +
            "        inner join GearboxEntity g On g.id = adv.gearbox\n " +
    "WHERE adv.id = :id")
    List<Object[]> findAdvertById(@Param("id") Long id);
}

