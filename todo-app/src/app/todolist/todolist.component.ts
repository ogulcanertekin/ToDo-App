import { Component, OnInit } from '@angular/core';
import { todoList } from '../models/todoList';
import { TodolistService } from '../services/todolist.service';

@Component({
  selector: 'app-todolist',
  templateUrl: './todolist.component.html',
  styleUrls: ['./todolist.component.css'],
  providers: [TodolistService]
})
export class TodolistComponent implements OnInit {

  constructor(private todolistService: TodolistService) { }

  todolists: todoList[]

  ngOnInit() {
    this.todolistService.getTodoLists().subscribe(data => {
      console.log(data)
      this.todolists = data
    });
  }

}
