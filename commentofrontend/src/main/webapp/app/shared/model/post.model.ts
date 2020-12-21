export interface IPost {
  id?: number;
  title?: string;
  body?: string;
  userid?: number;
}

export class Post implements IPost {
  constructor(public id?: number, public title?: string, public body?: string, public userid?: number) {}
}
