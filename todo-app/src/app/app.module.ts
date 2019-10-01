import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from "@angular/common/http";
import { RouterModule } from '@angular/router'
import { appRoutes } from './routes'
import { FormsModule, ReactiveFormsModule } from '@angular/forms'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';
import { AlertifyService } from './services/alertify.service'
import { RegisterComponent } from './register/register.component';
import { TodolistComponent } from './todolist/todolist.component';
import { TodolistCreateComponent } from './todolist/todolist-create/todolist-create.component'
import { TodolistDetailsComponent } from './todolist/todolist-details/todolist-details.component'
import { TodoitemCreateComponent } from './todolist/todoitem-create/todoitem-create.component';


@NgModule({
   declarations: [
      AppComponent,
      NavComponent,
      RegisterComponent,
      TodolistComponent,
      TodolistCreateComponent,
      TodolistDetailsComponent,
      TodoitemCreateComponent
   ],
   imports: [
      BrowserModule,
      AppRoutingModule,
      HttpClientModule,
      RouterModule.forRoot(appRoutes),
      FormsModule,
      ReactiveFormsModule
   ],
   providers: [
      AlertifyService
   ],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule { }
