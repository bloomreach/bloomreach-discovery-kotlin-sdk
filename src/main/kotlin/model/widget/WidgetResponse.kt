package model.widget

import com.fasterxml.jackson.annotation.JsonProperty

data class WidgetResponse(
    @JsonProperty("br_iuid")
    val brIuid: String,
    @JsonProperty("br_related_rid")
    val brRelatedRid: String,
    @JsonProperty("br_related_rid_tag")
    val brRelatedRidTag: String,
    @JsonProperty("more-results")
    val moreResults: List<MoreResult>,
    @JsonProperty("related-category")
    val relatedCategory: List<RelatedCategory>,
    @JsonProperty("related-item")
    val relatedItem: List<RelatedItem>
)