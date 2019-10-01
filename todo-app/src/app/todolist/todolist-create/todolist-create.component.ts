import { Component, OnInit } from '@angular/core';
import { TodolistService } from "../../services/todolist.service";
import {
  FormGroup,
  FormControl,
  Validators,
  FormBuilder
} from "@angular/forms";
import { todoList } from 'src/app/models/todoList';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-todolist-create',
  templateUrl: './todolist-create.component.html',
  styleUrls: ['./todolist-create.component.css'],
  providers: [TodolistService]
})
export class TodolistCreateComponent implements OnInit {

  constructor(
    private todolistService: TodolistService,
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) { }

  todoList: todoList;
  todoListCreateForm: FormGroup;

  createToDoListForm() {
    this.todoListCreateForm = this.formBuilder.group({
      name: ["", Validators.required],
    });
  }

  ngOnInit() {
    this.createToDoListForm();
  }

  add() {
    if (this.todoListCreateForm.valid) {
      this.todoList = Object.assign({}, this.todoListCreateForm.value)
      this.todolistService.add(this.todoList);
    }
  }
}


