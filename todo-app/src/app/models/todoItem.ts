import { StatusEnum } from "./StatusEnum.enum";

export class todoItem {
    name:string;
    description:string;
    status:StatusEnum;
    deadline:string;
    todoItemId:string;
    todoListId:string;
}
