import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { IComments, Comments } from 'app/shared/model/comments.model';
import { CommentsService } from './comments.service';
import { IPost } from '../../shared/model/post.model';

@Injectable({ providedIn: 'root' })
export class CommentsResolve implements Resolve<IComments> {
  constructor(private service: CommentsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPost> | Observable<never> {
    const id = route.params['id'];

    return of(new Comments());
  }
}

export const commentsRoute: Routes = [];
