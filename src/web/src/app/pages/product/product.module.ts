import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductComponent } from './product.component';
import {DashboardRoutingModule} from "./product.routing.module";


@NgModule({
  imports: [
    CommonModule,
    DashboardRoutingModule,
   
  ],
  declarations: [ProductComponent]
})
export class ProductModule { }
