<@layout.block title="Search Results"  >
    <#if books?has_content>
    <p>Your search was sorted by relevance.</p>
    <ul class="book-list">
        <#list books as book>
            <li class="book">
                <a href="/twu/viewbook" class="add-book">
                    <img class="book-picture" src="${book.image}"/>

                    <div class="book-content">
                        <h3 class="book-title">${book.title}</h3>

                        <div class="book-author">
                            <#if book.author?has_content>
                                by ${book.author}
                            </#if>
                        </div>
                    </div>
                </a>
            </li>
        </#list>
    </ul>
    </#if>
    <#if error??>
    <p id="error">${error}</p>
    </#if>
</@layout.block>