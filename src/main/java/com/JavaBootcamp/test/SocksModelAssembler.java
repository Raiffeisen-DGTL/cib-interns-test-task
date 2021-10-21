package com.JavaBootcamp.test;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class SocksModelAssembler implements RepresentationModelAssembler<Socks, EntityModel<Socks>> {
    @Override
    public EntityModel<Socks> toModel(Socks socks) {

        return EntityModel.of(socks,
                linkTo(methodOn(SocksController.class).one(socks.getId())).withSelfRel(),
                linkTo(methodOn(SocksController.class).all()).withRel("socks"));
    }
}
