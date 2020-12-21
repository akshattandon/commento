import { IPost } from 'app/shared/model/post.model';

export interface IComments {
  id?: number;
  name?: string;
  email?: string;
  body?: string;
  postId?: number;
}

export class Comments implements IComments {
  constructor(public id?: number, public name?: string, public email?: string, public body?: string, public postId?: number) {}
}
