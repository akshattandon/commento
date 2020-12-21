import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPost, Post } from 'app/shared/model/post.model';
import { CommentsService } from '../comments/comments.service';
import { Comments, IComments } from '../../shared/model/comments.model';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { User } from '../../core/user/user.model';
import { Observable } from 'rxjs';
import { AccountService } from '../../core/auth/account.service';

@Component({
  selector: 'jhi-post-detail',
  templateUrl: './post-detail.component.html',
})
export class PostDetailComponent implements OnInit {
  post: IPost = new Post();
  comments: IComments[] = [];
  comment: IComments = new Comments();
  isSaving: Boolean = false;

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected commentsService: CommentsService,
    protected accountService: AccountService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(res => this.loadPost(res));
  }
  loadPost(data: any): void {
    this.post = data.post;
    if (this.post) {
      // eslint-disable-next-line @typescript-eslint/ban-ts-ignore
      // @ts-ignore
      this.commentsService.find(this.post.id).subscribe((res: HttpResponse<IComments[]>) => this.allComments(res.body, res.headers));
    }
  }

  allComments(data: IComments[], headers: HttpHeaders): void {
    this.comments = data;
  }

  previousState(): void {
    window.history.back();
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  save(): void {
    this.isSaving = true;
    this.comment.postId = this.post.id;
    this.subscribeToSaveResponse(this.commentsService.create(this.comment));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IComments>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
