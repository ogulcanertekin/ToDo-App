import { Routes } from "@angular/router"
import { RegisterComponent } from './register/register.component';
import { TodolistComponent } from './todolist/todolist.component';
import { TodolistCreateComponent } from './todolist/todolist-create/todolist-create.component';
import { TodolistDetailsComponent } from './todolist/todolist-details/todolist-details.component'
import { TodoitemCreateComponent } from './todolist/todoitem-create/todoitem-create.component';

export const appRoutes: Routes = [
  { path: "register", component: RegisterComponent },
  { path: "todolists", component: TodolistComponent },
  { path: "todolistcreate", component: TodolistCreateComponent },
  { path: "mytodolists/:todolistId", component: TodolistDetailsComponent },
  { path: "todolists/:todolistId/createtodoitem", component: TodoitemCreateComponent },
  { path: "**", redirectTo: "todolists", pathMatch: "full" }
];