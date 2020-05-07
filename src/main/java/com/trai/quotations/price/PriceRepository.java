package com.trai.quotations.price;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface PriceRepository extends ReactiveMongoRepository<Eurusd, String> {

//    @Query("{ id: { $exists: true }}")
//    Flux<Eurusd> getEurusd(final Pageable page);

//    Flux<Eurusd> findByOrderByTimestampDesc();

//    @Autowired
//    private MongoDatabase mongoDatabase;

//    public void findAll(String asset) {
//        MongoCollection<Document> collection = mongoDatabase.getCollection(asset);
//        List<Price> prices = new ArrayList<>();
//
//        try (var cursor = collection.find().iterator()) {
//            while (cursor.hasNext()) {
//                Document obj = cursor.next();
//
////                String id = (String) obj.get("_id");
//                double open = (double) obj.get("open");
//                double high = (double) obj.get("high");
//                double low = (double) obj.get("low");
//                double close = (double) obj.get("close");
//                Date timestamp = (Date) obj.get("timestamp");
//
//                Price price = new Price(open, high, low, close, timestamp);
//                prices.add(price);
//
////                for (var entry: obj.entrySet()) {
////                    System.out.println(entry.getKey() + " / " + entry.getValue() + " / " + entry.getValue().getClass());
////                }
//
//            }
//
//        }


//    }

}


//@Repository
//public interface PriceRepository extends MongoRepository<Price, String> {
//
//    public List<Price> findAll();
//
//
//}


