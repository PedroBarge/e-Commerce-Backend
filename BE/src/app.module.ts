import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Product } from './Product/Entity/product.entity'; 
import { ProductModule } from './Product/product.module';

@Module({
  imports: [
    TypeOrmModule.forRoot({
      type: 'postgres',
      host: 'localhost',
      port: 5454,
      username: 'admin',
      password: '12345',
      database: 'store',
      entities: [Product],
      synchronize: true,
    }),
    ProductModule, // Import the ProductModule
  ],
  controllers: [],
  providers: [],
})
export class AppModule {}
