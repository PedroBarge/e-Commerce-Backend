import { Controller, Get, Post, Body } from '@nestjs/common';
import { ProductService } from '../Service/product.service';
import { Product } from '../Entity/product.entity';

@Controller('products')
export class ProductController {
  constructor(private readonly productService: ProductService) {}

  @Get()
  findAll(): Promise<Product[]> {
    return this.productService.findAll();
  }
  
  @Post()
  create(@Body() createProductDto: Product): Promise<Product> {
    return this.productService.create(createProductDto);
  }
}
