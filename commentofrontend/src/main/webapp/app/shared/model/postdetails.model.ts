import { Comments, IComments } from './comments.model';

export interface IPostDetails {
  id?: number;
  title?: string;
  body?: string;
  userId?: number;
  comments?: IComments[];
}

export class PostDetails implements IPostDetails {
  constructor(public id?: number, public title?: string, public body?: string, public userId?: number, public comments?: Comments[]) {}
}
