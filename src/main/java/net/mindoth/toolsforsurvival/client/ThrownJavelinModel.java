package net.mindoth.toolsforsurvival.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class ThrownJavelinModel extends Model {
    private final ModelPart javelin;

    public ThrownJavelinModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.javelin = root.getChild("javelin");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition javelin = partdefinition.addOrReplaceChild("javelin", CubeListBuilder.create().texOffs(4, 0).addBox(-2.0F, -29.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.0F, -30.0F, 0.0F, 1.0F, 30.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 0).addBox(0.0F, -29.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        javelin.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
