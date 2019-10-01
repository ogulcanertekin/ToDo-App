import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { todoList } from '../models/todoList';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { todoItem } from '../models/todoItem';
import { AlertifyService } from './alertify.service';
import { Router } from '@angular/router';
import { StatusEnum } from '../models/StatusEnum.enum';

@Injectable({
  providedIn: 'root'
})
export class TodolistService {

  constructor(
    private httpClient: HttpClient,
    private alertifyService: AlertifyService,
    private router: Router,
  ) { }

  userId = localStorage.getItem("userId");
  path = "http://localhost:8080/users/";
  header = new HttpHeaders();
  headersConfig1 = this.header.append("Content-Type", "application/json");
  headersConfig2 = this.headersConfig1.append("Authorization", localStorage.getItem("token"));
  headersConfig3 = this.headersConfig2.append("Accept", "application/json");

  PostCompletedStatus: any = {};

  getTodoLists(): Observable<todoList[]> {
    return this.httpClient.get<todoList[]>(this.path + this.userId + "/todolists", { headers: this.headersConfig3 });
  }

  add(todoList) {
    this.httpClient.post(this.path + this.userId + "/todolists", todoList, { headers: this.headersConfig3 }).subscribe(data => {
      this.alertifyService.success("todo List added successfully")
      this.router.navigateByUrl("/todolists");
    });
  }

  getTodoItemsByTodoList(todoListId): Observable<todoItem[]> {
    return this.httpClient.get<todoItem[]>(this.path + "todolists/" + todoListId + "/todoitems", { headers: this.headersConfig3 });
  }

  addToDoItem(todoItem, todoListId) {
    this.httpClient.post(this.path + "todolists/" + todoListId + "/todoitems", todoItem, { headers: this.headersConfig3 }).subscribe(data => {
      this.alertifyService.success("todo Item added successfully")
      this.router.navigateByUrl("mytodolists/" + todoListId);
    });
  }

  PostToDoItemStatus(todoitemId, todoListId) {

    this.PostCompletedStatus.todoItemId = todoitemId;
    this.PostCompletedStatus.status = StatusEnum.Completed;
    console.log(this.PostCompletedStatus);
    this.httpClient.put(this.path + "todoitems/" + todoitemId, this.PostCompletedStatus, { headers: this.headersConfig3 }).subscribe(data => {
      this.alertifyService.success("To-Do Item marked as Complete Succesfully");
      this.router.navigateByUrl("mytodolists/" + todoListId);

    });
  }

}



