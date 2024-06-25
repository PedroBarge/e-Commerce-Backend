import { Module } from "@nestjs/common";
import { Product } from "./Entity/product.entity";
import { TypeOrmModule } from "@nestjs/typeorm";
import { ProductService } from "./Service/product.service";
import { ProductController } from "./Controller/product.controller";

@Module({
    imports: [TypeOrmModule.forFeature([Product])],
    controllers: [ProductController],
    providers: [ProductService],
    exports: [ProductService]
})

export class ProductModule {}