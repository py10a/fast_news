//
//  ArticlesScreen.swift
//  iosApp
//

import SwiftUI
import shared

extension ArticlesScreen {
    
    @MainActor
    class ArticlesViewModelWrapper : ObservableObject {
        let articlesViewModel: ArticlesViewModel
        @Published var articlesState: ArticlesState
        
        init() {
            self.articlesViewModel = ArticlesViewModel()
            self.articlesState = articlesViewModel.articlesState.value
        }
        
        func startObserving() {
            Task {
                for await articlesS in articlesViewModel.articlesState {
                    self.articlesState = articlesS
                }
            }
        }
        
    }
}

struct ArticlesScreen: View {
    @ObservedObject private(set) var viewModel : ArticlesViewModelWrapper
    
    var body: some View {
        VStack {
            if viewModel.articlesState.isLoading {
                LoadingView(message: "Loading")
            }
            else if viewModel.articlesState.error != nil {
                ErrorView(message: "Error!")
            }
            else if viewModel.articlesState.articles.isEmpty {
                EmptyView(message: "Nothing to show!")
            }
            else if (!viewModel.articlesState.articles.isEmpty) {
                ScrollView {
                    LazyVStack(alignment: .leading, spacing: 10.0) {
                        ForEach(viewModel.articlesState.articles, id: \.self) { article in
                            ArticlesItemView(article: article)
                        }
                    }
                }
            }
        }.onAppear {
            self.viewModel.startObserving()
        }
    }
}

struct ArticlesItemView: View {
    var article: Article
    
    var body: some View {
        VStack (alignment: .leading, spacing: 8.0) {
            AsyncImage(url: URL(string: article.imageUrl))
            Text(article.title)
                .font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/)
                .bold()
            Text(article.desc)
            Text(article.date)
                .frame(
                     maxWidth: .infinity,
                     alignment: .trailing
                )
                .foregroundStyle(.gray)
        }
        .padding(16)
    }
}

// ************************
// View States
// ************************

struct EmptyView: View {
    var message: String
    
    var body: some View {
        Text(message)
            .font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/)
        }
    }

struct LoadingView: View {
    var message: String
    
    var body: some View {
        ProgressView(message)
    }
}

struct ErrorView: View {
    var message: String
    
    var body: some View {
        Text(message)
            .font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/)
    }
}

#Preview {
    ArticlesScreen(viewModel: .init())
//    ArticlesScreen()
}
