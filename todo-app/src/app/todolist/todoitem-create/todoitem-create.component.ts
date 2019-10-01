import { Component, OnInit } from '@angular/core';
import { TodolistService } from "../../services/todolist.service";
import { ActivatedRoute } from '@angular/router';

import {
  FormGroup,
  FormControl,
  Validators,
  FormBuilder,
} from "@angular/forms";
import { todoItem } from 'src/app/models/todoItem';


@Component({
  selector: 'app-todoitem-create',
  templateUrl: './todoitem-create.component.html',
  styleUrls: ['./todoitem-create.component.css'],
  providers: [TodolistService]
})
export class TodoitemCreateComponent implements OnInit {

  constructor(
    private activatedRoute: ActivatedRoute,
    private todolistService: TodolistService,
    private formBuilder: FormBuilder
  ) { }

  todoItem: todoItem;
  todoItemCreateForm: FormGroup;
  todolistId: string;

  createToDoItemForm() {
    this.todoItemCreateForm = this.formBuilder.group({           //Reactive Forms- city-name / city description we need!
      name: ["", Validators.required],
      description: ["", Validators.required],
      deadline: ["", Validators.required],
    });
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.todolistId = params["todolistId"];
    });
    this.createToDoItemForm();
  }

  add() {
    if (this.todoItemCreateForm.valid) {
      this.todoItem = Object.assign({}, this.todoItemCreateForm.value)
      this.todolistService.addToDoItem(this.todoItem, this.todolistId);
    }
  }
}
