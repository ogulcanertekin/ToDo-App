import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { todoItem } from '../../models/todoItem';
import { TodolistService } from '../../services/todolist.service';


@Component({
  selector: 'app-todolist-details',
  templateUrl: './todolist-details.component.html',
  styleUrls: ['./todolist-details.component.css'],
  providers: [TodolistService]
})
export class TodolistDetailsComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private todolistService: TodolistService) { }

  searchTerm: string;
  todolistId: string;
  todoitems: todoItem[] = [];

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.getToDoItemsByList(params["todolistId"]);
      this.todolistId = params["todolistId"];
      console.log(params["todolistId"]);
    });
  }

  MarkStatusAsCompleted(todoItemId) {
    this.todolistService.PostToDoItemStatus(todoItemId, this.todolistId)
  }

  getToDoItemsByList(todoListId) {
    this.todolistService.getTodoItemsByTodoList(todoListId).subscribe(data => {
      this.todoitems = data;
    })
  }

  Search() {
    if (this.searchTerm != "") {
      this.todoitems = this.todoitems.filter(res => {
        return res.name.toLowerCase().match(this.searchTerm.toLowerCase());
      });

    } else if (this.searchTerm == "") {
      this.ngOnInit(); 1
    }

  }

}
