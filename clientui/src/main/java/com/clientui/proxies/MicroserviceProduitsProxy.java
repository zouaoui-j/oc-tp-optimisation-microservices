package com.clientui.proxies;

import com.clientui.beans.ProductBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(name = "microservice-produits", url = "localhost:9001", decode404 = true)
// On ne cible plus le microservice cible mais on passe par le gateway
//@FeignClient(name = "microservice-produits"
            // plus besoin  de fournir l'url, utilisera le load balancer en se basant sur les instances dans eureka
//          , url = "localhost:9001"
//)
@FeignClient(name = "gateway-server", contextId = "microserviceProduitsProxy")
public interface MicroserviceProduitsProxy {

    //On passe par le gateway et son routage à la place du microservice cible
    //@GetMapping(value = "/Produits")
    @GetMapping(value = "/microservice-produits/Produits")
    public List<ProductBean> listeDesProduits();

    //On passe par le gateway et son routage à la place du microservice cible
    //@GetMapping( value = "/Produits/{id}")
    @GetMapping( value = "/microservice-produits/Produits/{id}")
    public ProductBean recupererUnProduit(@PathVariable("id") int id);
}
